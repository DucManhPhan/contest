// https://www.hackerrank.com/challenges/tutorial-intro?h_r=next-challenge&h_v=zen

#include <iostream>
#include <algorithm>  // std::find
#include <vector>


int searchElement(const std::vector<int>& vecNumber, const int nSearchedElement) {
	std::vector<int>::const_iterator it, it_begin = vecNumber.cbegin(), it_end = vecNumber.cend();

	it = std::find(it_begin, it_end, nSearchedElement);
	if (it != it_end) {
		return std::distance(it_begin, it);
	}
	else {
		return 0;
	}
}



int main()
{
	std::vector<int> vecNumber = {1, 4, 5, 7, 9, 12};
	int nSearchedElement = 12;
	
	int nIndex = searchElement(vecNumber, nSearchedElement);

	std::cout << nIndex;

	system("pause");
	return 0;
}