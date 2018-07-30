// https://www.hackerrank.com/challenges/quicksort4?h_r=next-challenge&h_v=zen

#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>

int nSwap_InsertionSort = 0;
int nSwap_QuickSort = 0;

void swap(int& a, int& b)
{
	int tmp = a;
	a = b;
	b = tmp;
}

void insertionSort(std::vector<int>& vec)
{
	int size = vec.size();

	for (int i = 1; i < size; ++i)
	{
		for (int j = i; j > 0; --j)
		{
			if (vec[j] < vec[j - 1])
			{
				swap(vec[j], vec[j - 1]);
				++nSwap_InsertionSort;
			}
		}
	}
}

int partition(std::vector<int>& a, int start, int end)
{
	int i = start - 1;
	int pivot = a[end];

	for (int j = start; j < end; ++j)
	{
		if (a[j] < pivot)
		{
			++i;
			swap(a[i], a[j]);
			++nSwap_QuickSort;
		}
	}

	swap(a[i + 1], a[end]);
	++nSwap_QuickSort;

	return i + 1;
}

void quickSort(std::vector<int>& vec, int start, int end)
{
	if (start < end)
	{
		int mid = partition(vec, start, end);

		quickSort(vec, start, mid - 1);
		quickSort(vec, mid + 1, end);
	}
}


int main()
{
	std::vector<int> vec = { 1, 3, 4, 5, 2, 7, 8, 9, 6, 10 };//{ 1, 3, 9, 8, 2, 7, 5 };
	int n = vec.size();

	std::vector<int> tmpvec(vec.begin(), vec.end());

	insertionSort(vec);
	quickSort(tmpvec, 0, n - 1);

	std::cout << nSwap_InsertionSort - nSwap_QuickSort;

	system("pause");
	return 0;
}