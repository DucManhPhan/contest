// https://www.hackerrank.com/challenges/find-the-median

// https://medium.com/omarelgabrys-blog/the-angry-birds-chuck-99151b62aa0e - dung shuffle de xao tron mang truoc khi sap xep.

#include <iostream>
#include <algorithm>
#include <vector>
#include <iterator>

using namespace std;

void swap(int& a, int& b)
{
	int tmp = a;
	a = b;
	b = tmp;
}

int partition(std::vector<int>& a, int start, int end)
{
	int pivot = a[end];
	int i = start - 1; 

	for (int j = start; j < end; ++j)
	{
		if (a[j] < pivot)
		{
			++i;
			swap(a[i], a[j]);
		}
	}

	swap(a[i + 1], a[end]);
	return i + 1;
}

// ManhPD5 - Hoare partitioning - start - phuong phap nay khong phu hop.
//int partition(std::vector<int>& vec, int start, int end)
//{
//	int mid = (start + end) / 2;
//	int pivot = vec[mid];
//	int i = start, j = end;
//
//	while (i <= j)
//	{
//		while (vec[i] < pivot) ++i;
//		while (vec[j] > pivot) --j;
//
//		if (i <= j)
//		{
//			if (i < j)
//				swap(vec[i], vec[j]);
//
//			++i;
//		}
//	}
//
//	return j;
//}
// ManhPD5 - Hoare partitioning - end


// ManhPD5 - Lomuto partitioning - start
//int partition(vector<int> &vec, int left, int right)
//{
//	int pivotIndex = (left + right) / 2; //left + rand() % (right - left);
//	int pivot = vec[pivotIndex];
//	int partitionIndex = left;
//
//	swap(vec[pivotIndex], vec[right]);
//	for (int i = left; i < right; i++) {
//		if (vec[i] < pivot) {
//			swap(vec[i], vec[partitionIndex]);
//			partitionIndex++;
//		}
//	}
//	swap(vec[partitionIndex], vec[right]);
//
//	return partitionIndex;
//}
// ManhPD5 - Lomuto partitioning - end

int select(vector<int> &vec, int left, int right, int k)
{
	if (right == left) {
		return vec[left];
	}
	
	int pivotIndex = partition(vec, left, right);
	if (pivotIndex == k) {
		return vec[k];
	}
	else if (k < pivotIndex) {
		/*k is present on the left size of pivotIndex*/
		return select(vec, left, pivotIndex - 1, k);
	}
	else {
		/*k occurs on the right size of pivotIndex*/
		return select(vec, pivotIndex + 1, right, k);
	}
	return 0;
}


//void quickSort(std::vector<int>& a, int start, int end)
//{
//	if (start < end)
//	{
//		int mid = partition(a, start, end);
//
//		quickSort(a, start, mid - 1);
//		quickSort(a, mid + 1, end);
//	}
//}

int main()
{
	std::vector<int> vec = { 0, 1, 2, 4, 6, 5, 3 }; //{9, 12, 5, 6, 4, 3, 15, 13, 8, 2, 1}; //{3, 5, 33, 1, 8, 12, 4, 23, 8}; //{ 1, 3, 9, 8, 2, 7, 5 }; //{ 0, 1, 2, 4, 6, 5, 3 };
	int size = vec.size();

	int median = select(vec, 0, size - 1, size / 2);

	std::cout << median;

	/*quickSort(vec, 0, size - 1);

	std::copy(vec.begin(), vec.end(), std::ostream_iterator<int>(std::cout, " "));
	std::cout << std::endl;*/

	system("pause");
	return 0;
}