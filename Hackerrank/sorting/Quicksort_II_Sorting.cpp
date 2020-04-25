// https://www.hackerrank.com/challenges/quicksort2

#include <iostream>
#include <vector>
//#include <algorithm>
#include <iterator>

void printArray(const std::vector<int>& vecNumber, int start, int end)
{
	if (end - start < 1)
		return;

	for (int i = start; i <= end; ++i)
	{
		std::cout << vecNumber[i] << " ";
	}

	std::cout << std::endl;
}


//void swap(int& a, int& b)
//{
//	int temp = a;
//	a = b;
//	b = temp;
//}
//
//
//// Hoare partition khong tra ve dung vi tri cua pivot, do do, ta in sai ket qua. 
//int partition(std::vector<int>& vec, int start, int end)
//{
//	int i = start -1; 
//	int j = end + 1; 
//	int pivot = vec[start];
//
//	while (1)
//	{
//		do {
//			++i;
//		} while (vec[i] < pivot);
//
//		do {
//			--j;
//		} while (vec[j] > pivot);
//
//		if (i >= j)
//			return j;
//
//		swap(vec[j], vec[i]);
//	}
//}
//
//
//void quickSort(std::vector<int>& vec, int start, int end)
//{
//	if (start < end)
//	{
//		int mid = partition(vec, start, end);
//
//		quickSort(vec, start, mid);
//		quickSort(vec, mid + 1, end);
//		printArray(vec, start, end);
//	}
//}


// Code nay nhin chung dai dong nhung non len duoc ban chat cua quicksort.
// phan thanh 2 day dua vao pivot, sau do, de quy sap xep cho 2 day nay.
// cach nay de lam hon voi truong hop: "repeated elements".
//void quickSort(std::vector<int>& vec)
//{
//	int size = vec.size();
//	if (size < 2)
//		return;
//
//	std::vector<int> leftArray; 
//	std::vector<int> rightArray; 
//
//	int pivot = vec[0];
//
//	for (int i = 1; i < size; ++i)
//	{
//		if (vec[i] <= pivot)
//			leftArray.push_back(vec[i]);
//		else
//			rightArray.push_back(vec[i]);
//	}
//
//	quickSort(leftArray);
//	quickSort(rightArray);
//
//	int index = 0;
//
//	for (unsigned int l = 0; l < leftArray.size(); ++l)
//	{
//		vec[index++] = leftArray[l];
//		std::cout << leftArray[l] << " ";
//	}
//
//	vec[index++] = pivot; 
//	std::cout << pivot << " ";
//
//	for (unsigned int r = 0; r < rightArray.size(); ++r)
//	{
//		vec[index] = rightArray[r];
//		std::cout << rightArray[r] << " ";
//	}
//
//	std::cout << std::endl;
//}


// Cach khac dung 2 vector left va right luu cac phan tu. 
void _copy(std::vector<int>& vIntermediate, std::vector<int>& vec, int startIndex)
{
	for (int i : vIntermediate)
	{
		vec[startIndex++] = i;
	}
}



int partition(std::vector<int>& vec, int start, int end)
{
	int p = vec[start];
	std::vector<int> left, right;

	for (int i = start + 1; i <= end; ++i)
	{
		if (vec[i] > p)
			right.push_back(vec[i]);
		else
			left.push_back(vec[i]);
	}

	_copy(left, vec, start);

	int ppos = left.size() + start; 
	vec[ppos] = p;

	_copy(right, vec, ppos + 1);

	return ppos;
}


void quickSort(std::vector<int>& vec, int start, int end)
{
	if (start >= end)
		return;

	int pos = partition(vec, start, end);
	quickSort(vec, start, pos - 1);
	quickSort(vec, pos + 1, end);

	printArray(vec, start, end);
}


int main()
{
	std::vector<int> vecNumber = { 5, 8, 1, 3, 7, 9, 2 }; //{ 3, 5, 33, 1, 8, 12, 4, 23, 8 };
	int size = vecNumber.size();

	quickSort(vecNumber, 0, size - 1);
	//quickSort(vecNumber);

	system("pause");
	return 0;
}