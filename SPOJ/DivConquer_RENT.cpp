#include <iostream>
#include <vector>

struct Good
{
	int startTime; 
	int duration; 
	int cost;
};

int partition(std::vector<Good *> &tmpGoods, int start, int end)
{
	int mid = (start + end) / 2;

	int i = start; 
	int j = end;
	

	while (i < j)
	{
		while (tmpGoods[i]->cost < tmpGoods[mid]->cost) ++i;
		while (tmpGoods[j]->cost > tmpGoods[mid]->cost) --j;

		if (i < j)
		{
			std::swap(tmpGoods[i], tmpGoods[j]);
			++i; 
			--j;
		}
	}

	return i;
}

void QuickSort(std::vector<Good *> &tmpGoods, int start, int end)
{
	if (start < end)
	{
		int mid = partition(tmpGoods, start, end);
		QuickSort(tmpGoods, start, mid);
		QuickSort(tmpGoods, mid + 1, end);
	}
}

int CheckMaxGoods(std::vector<Good *> tmpGoods, int num)
{
	int size = tmpGoods.size();
	int tmpMaxCost = 0;
	int maxCost = 0;

	for (int i = size - 1; i > 0; --i)
	{
		Good *pMax = tmpGoods[i];

		for (int j = i - 1; j >= 0; --j)
		{
			if (pMax->startTime + pMax->duration < tmpGoods[j]->startTime)
			{
				tmpMaxCost = pMax->cost + tmpGoods[j]->cost;
			}
		}

		if (maxCost < tmpMaxCost)
		{
			maxCost = tmpMaxCost;
		}
	}

	return maxCost;
}

void PrintMaxCost(std::vector<Good *> &tmpGoods)
{
	int num = tmpGoods.size(); 

	// 1. Sap xep vector nay theo thu tu tang dan theo chi phi. 
	QuickSort(tmpGoods, 0, num - 1);

	// 2. Xet xem cac don hang nao co chi phi lon nhat, kem theo kiem tra dieu kien ve thoi gian.
	int max = CheckMaxGoods(tmpGoods, num);

	std::cout << max << std::endl;
}

int main()
{
	int T = 0;
	std::cin >> T; 

	for (int i = 0; i < T; ++i)
	{
		int n; 
		std::cin >> n; 

		std::vector<Good *> vecGood;
		for (int j = 0; j < n; ++j)
		{
			Good *pGood = new Good();
			std::cin >> pGood->startTime >> pGood->duration >> pGood->cost;

			vecGood.push_back(pGood);
		}

		std::cout << "Chi phi lon nhat thu duoc la: \n";
		PrintMaxCost(vecGood);

		for (int k = 0; k < n; ++k)
		{
			delete vecGood[k];
		}
	}


	system("pause");
	return 0;
}