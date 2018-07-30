// https://www.hackerrank.com/challenges/insertionsort1?h_r=next-challenge&h_v=zen

#include <iostream>
#include <vector>
#include <algorithm>
#include <iterator>

// ManhPD5
//void InsertionSort(std::vector<int>& vecNumber, int number) {
//	std::vector<int>::reverse_iterator it, it_rbegin = vecNumber.rbegin(), it_rend = vecNumber.rend();
//	int nReserved = vecNumber[number - 1];
//	int nIndex = 0;
//
//	for (it = it_rbegin + 1; it != it_rend; ++it)
//	{
//		if (*it > nReserved)
//		{
//			*(it - 1) = *it; 
//
//			std::copy(vecNumber.begin(), vecNumber.end(), std::ostream_iterator<int>(std::cout, " "));
//			std::cout << std::endl;
//		}
//		else
//		{
//			*(it - 1) = nReserved;
//			++nIndex;
//
//			std::copy(vecNumber.begin(), vecNumber.end(), std::ostream_iterator<int>(std::cout, " "));
//			std::cout << std::endl;
//
//			break;
//		}
//	}
//
//	if (nIndex == 0)
//	{
//		*(it - 1) = nReserved;
//
//		std::copy(vecNumber.begin(), vecNumber.end(), std::ostream_iterator<int>(std::cout, " "));
//		std::cout << std::endl;
//	}
//
//}


// Wikipedia.
void InsertionSort(std::vector<int>& vecNumber, int nNumber)
{
	int tmp = vecNumber[nNumber - 1];
	int j = nNumber - 1;

	while (j > 0 && vecNumber[j - 1] > tmp)
	{
		vecNumber[j] = vecNumber[j - 1];

		std::copy(vecNumber.begin(), vecNumber.end(), std::ostream_iterator<int>(std::cout, " "));
		std::cout << std::endl;

		--j;
	}

	vecNumber[j] = tmp;

	std::copy(vecNumber.begin(), vecNumber.end(), std::ostream_iterator<int>(std::cout, " "));
	std::cout << std::endl;
}

int main()
{
	std::vector<int> vecNumber = { 2, 4, 6, 8, 1 };//{2, 4, 6, 8, 1};//{ 2, 4, 6, 8, 6 };//{ 2, 4, 6, 8, 3 }; //{2, 4, 6, 8, 5}
	int nNum = vecNumber.size();

	InsertionSort(vecNumber, nNum);

	system("pause");
	return 0;
}