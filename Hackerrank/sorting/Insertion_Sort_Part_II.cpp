// https://www.hackerrank.com/challenges/insertionsort2?h_r=next-challenge&h_v=zen

#include <iostream>
#include <iterator>
#include <algorithm>
#include <vector>


void printArray(const std::vector<int>& vecNumber)
{
	std::copy(vecNumber.begin(), vecNumber.end(), std::ostream_iterator<int>(std::cout, " "));
	std::cout << std::endl;
}


void swap(int& a, int& b)
{
	int tmp = a; 
	a = b; 
	b = tmp;
}


//void insertionSort(std::vector<int>& vecNumber)
//{
//	int size = vecNumber.size();
//
//	for (int i = 1; i < size; ++i)
//	{
//		int nSelected = vecNumber[i];
//		int j = i;
//
//		while (j > 0 && vecNumber[j - 1] > nSelected)
//		{
//			vecNumber[j] = vecNumber[j - 1];
//			--j;
//		}
//
//		vecNumber[j] = nSelected;
//
//		printArray(vecNumber);
//	}
//}


void insertionSort(std::vector<int>& vecNumber)
{
	int size = vecNumber.size();

	for (int i = 1; i < size; ++i)
	{
		for (int j = i; j > 0; --j)
		{
			if (vecNumber[j - 1] > vecNumber[j])
			{
				swap(vecNumber[j - 1], vecNumber[j]);
			}
		}

		printArray(vecNumber);
	}
}


int main()
{
	std::vector<int> vecNumber = { 1, 4, 3, 5, 6, 2 };

	insertionSort(vecNumber);

	system("pause");
	return 0;
}