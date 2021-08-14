#include <stack>
#include <string>
#include <vector>

/**
 * This is the interface for the expression tree Node.
 * You should not remove it, and you can define some classes to implement it.
 */

class Node {
public:
    virtual ~Node() {}
    virtual int evaluate() const = 0;
};


class NumNode : public Node {
public:
    NumNode(int val): _val (val) {}
    int evaluate() const;
private:
    int _val;
};
int NumNode::evaluate() const {
    return _val;
}


class OpNode : public Node {
public:
    OpNode(Node* left, Node* right) : _left(left), _right(right) {}
    ~OpNode();
protected:
    Node* const _left;
    Node* const _right;
};
OpNode::~OpNode() {
    delete _left;
    delete _right;
}


class AddOpNode : public OpNode {
public:
    AddOpNode(Node* left, Node* right): OpNode(left, right) {}
    int evaluate() const;
};
int AddOpNode::evaluate() const {
    return _left->evaluate() + _right->evaluate();
}


class SubtractOpNode : public OpNode {
public:
    SubtractOpNode(Node* left, Node* right): OpNode(left, right) {}
    int evaluate() const;
};
int SubtractOpNode::evaluate() const {
    return _left->evaluate() - _right->evaluate();
}


class MultiplyOpNode : public OpNode {
public:
    MultiplyOpNode(Node* left, Node* right): OpNode(left, right) {}
    int evaluate() const;
};
int MultiplyOpNode::evaluate() const {
    return _left->evaluate() * _right->evaluate();
}


class DivideOpNode : public OpNode {
public:
    DivideOpNode(Node* left, Node* right): OpNode(left, right) {}
    int evaluate() const;
};
int DivideOpNode::evaluate() const {
    return _left->evaluate() / _right->evaluate();
}


/**
 * This is the TreeBuilder class.
 * You can treat it as the driver code that takes the postinfix input
 * and returns the expression tree represnting it as a Node.
 */

class TreeBuilder {
private:
    Node* node_create(std::string op, Node* left, Node* right) {
        switch (op[0]) {
            case '+': return new AddOpNode(left, right);
            case '-': return new SubtractOpNode(left, right);
            case '*': return new MultiplyOpNode(left, right);
            case '/': return new DivideOpNode(left, right);
            default: return nullptr;
        }
    }

    int to_int(std::string &x) {
        int ret = 0;
        for (char ch : x) {
            ret = ret * 10 + (ch - '0');
        }
        return ret;
    }
public:
    /*
     * Create stack of nodes.
     * Pop two nodes when you encounter operator. Push the result.
     */
    Node* buildTree(std::vector<std::string>& postfix) {
        std::stack<Node*> st;

        for (auto &token : postfix) {
            if (isdigit(token[0])) {
                st.push(new NumNode(to_int(token)));
            } else {
                Node* right = st.top(); st.pop();
                Node* left = st.top(); st.pop();
                st.push(node_create(token, left, right));
            }
        }

        return st.top();
    }
};


/**
 * Your TreeBuilder object will be instantiated and called as such:
 * TreeBuilder* obj = new TreeBuilder();
 * Node* expTree = obj->buildTree(postfix);
 * int ans = expTree->evaluate();
 */
 
