// https://www.hackerrank.com/challenges/big-sorting
#include <iostream>
#include <algorithm>
#include <vector>
#include <string>
#include <iterator>
#include <cctype>

bool stringCompare(const std::string& left, const std::string& right)
{
	/*std::string::const_iterator lit_begin = left.begin(); 
	std::string::const_iterator lit_end = left.end();

	std::string::const_iterator rit_begin = right.begin();
	std::string::const_iterator rit_end = right.end();

	std::string::const_iterator lit, rit;*/

	if (left.size() < right.size())
	{
		return true;
	}
	else if (left.size() > right.size())
	{
		return false;
	}
	else
	{
		/*for (lit = lit_begin, rit = rit_begin; lit != lit_end, rit != rit_end; ++lit, ++rit)
		{
			return *lit > * rit ? true : false;
		}*/

		return left < right;
	}
}

int main()
{
	std::vector<std::string> strVector = {"31415926535897932384626433832795", "1", "3", "10", "3", "5"};

	std::sort(strVector.begin(), strVector.end(), stringCompare);

	std::reverse_copy(strVector.begin(), strVector.end(), std::ostream_iterator<std::string>(std::cout, "\n"));

	system("pause");
	return 0;
}