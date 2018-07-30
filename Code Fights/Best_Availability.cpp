#include <iostream>
#include <string>
#include <vector>


std::string smartAssigning(std::vector<std::string> names, std::vector<bool> statuses, std::vector<int> projects, std::vector<int> tasks) {
    int best_availability = 0;
    int length = statuses.size();
    
    std::vector<int> position;
    
    for (int i = 0; i < length; ++i){
        if (false == statuses[i]){
            position.push_back(i);
        }
    }
    
    int temp_length = position.size();
    for (int i = 0; i < temp_length - 1; ++i)
    {
        if (tasks[position[i]] == tasks[position[i + 1]]){
            if (projects[position[i]] < projects[position[i + 1]]){
                best_availability = position[i];
            }
            else{
                best_availability = position[i + 1];
            }
        }
        else if(tasks[position[i]] < tasks[position[i + 1]]) {
            best_availability = position[i];
        }
        else {
            best_availability = position[i + 1];
        }
    }
    
    return names[best_availability];
}

int main()
{
	std::string name[] = {"John", "Martin", "Luke"};
	std::vector<std::string> names(name, name + 3);

	bool status[] = {false, true, false};
	std::vector<bool> statuses(status, status + sizeof(status) / sizeof(bool));

	int project[] = {1, 0, 2};
	std::vector<int> projects(project, project + sizeof(project) / sizeof(int));

	int task[] = {2, 0, 1};
	std::vector<int> tasks(task, task + sizeof(task) + sizeof(int));

	std::cout << smartAssigning(names, statuses, projects, tasks) << std::endl;

	system("pause");

	return 0;
}



