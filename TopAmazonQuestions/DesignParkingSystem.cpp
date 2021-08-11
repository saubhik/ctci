#include <vector>

using namespace std;

class ParkingSystem {
private:
    vector<int> cars;
public:
    ParkingSystem(int big, int medium, int small) {
        cars = {big, medium, small};
    }

    bool addCar(int carType) {
        return cars[carType - 1]-- > 0;
    }
};

/**
 * Your ParkingSystem object will be instantiated and called as such:
 * ParkingSystem* obj = new ParkingSystem(big, medium, small);
 * bool param_1 = obj->addCar(carType);
 */
 
