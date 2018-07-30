// https://www.hackerrank.com/challenges/quicksort3

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>

void swap(int& a, int& b)
{
	int tmp = a; 
	a = b; 
	b = tmp;
}

void printArray(const std::vector<int>& vec)
{
	std::copy(vec.begin(), vec.end(), std::ostream_iterator<int>(std::cout, " "));
	std::cout << std::endl;
}


int partition(std::vector<int>& vec, int start, int end)
{
	int i = start - 1; 
	int pivot = vec[end];

	for (int j = start; j < end; ++j)
	{
		if (vec[j] < pivot)
		{
			++i; 
			swap(vec[i], vec[j]);
		}
	}

	swap(vec[i + 1], vec[end]);

	return i + 1;
}


void quickSort(std::vector<int>& vec, int start, int end)
{
	if (start < end)
	{
		int mid = partition(vec, start, end);
		printArray(vec);

		quickSort(vec, start, mid - 1);
		quickSort(vec, mid + 1, end);
	}
}

int main()
{
	std::vector<int> vec = { 1, 3, 9, 8, 2, 7, 5 };
	int n = vec.size();

	quickSort(vec, 0, n - 1);

	system("pause");
	return 0;
}