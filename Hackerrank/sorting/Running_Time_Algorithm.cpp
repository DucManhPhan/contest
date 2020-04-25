// https://www.hackerrank.com/challenges/runningtime

#include <iostream>
#include <vector>
#include <iterator>

void swap(int& a, int& b)
{
	int temp = a; 
	a = b; 
	b = temp;
}

void insertionSort(std::vector<int>& vecnumber, int& nOperation)
{
	int size = vecnumber.size();

	for (int i = 1; i < size; ++i)
	{
		for (int j = i; j > 0; --j)
		{
			if (vecnumber[j - 1] > vecnumber[j])
			{
				swap(vecnumber[j - 1], vecnumber[j]);
				++nOperation;
			}
		}
	}
}

int main()
{
	std::vector<int> vecNumber = { 2, 1, 3, 1, 2 };
	int nOperation = 0;

	insertionSort(vecNumber, nOperation);
	
	std::cout << nOperation;

	system("pause");
	return 0;
}