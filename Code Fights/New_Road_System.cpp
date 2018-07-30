// New Road System: https://codefights.com/arcade/graphs-arcade/kingdom-roads/nCMisf4ZKpDLdHevE/description

// Note : - all the roads in the kingdom from two - directional to one - directional.
//		  - Each city has the same number of incoming and outgoing roads. 

#include <iostream>
#include <vector>


bool newRoadSystem(std::vector<std::vector<bool>> roadRegister);

int main(){
	std::vector<std::vector<bool>> roadRegister;

	newRoadSystem(roadRegister);

	return 0;
}


bool newRoadSystem(std::vector<std::vector<bool>> roadRegister){
	if (roadRegister.empty()){
		return false;
	}

	// first  : incoming.
	// second : outgoing. 
	std::vector<std::pair<int, int>> Cities(roadRegister.size(), std::pair<int, int>(0, 0));

	for (int i = 0; i < roadRegister.size(); ++i){
		for (int j = 0; j < roadRegister[i].size(); ++j){
			if (true == roadRegister[i][j]) {
				++Cities[i].second;
				++Cities[j].first;
			}
			else if (true == roadRegister[j][i]) {
				++Cities[j].second;
				++Cities[i].first;
			}
			else {
				return false;
			}
		}
	}

	int length = Cities.size();
	for (int i = 0; i < length; ++i) {
		if (Cities[i].first != Cities[i].second) {
			return false;
		}
	}

	return true;
}