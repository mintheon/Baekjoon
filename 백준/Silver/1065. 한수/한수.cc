#include <iostream>

using namespace std;

int main() {

	int n, cnt = 99;
	int arr[3] = { 0 };

	cin >> n;

	// n이 100미만인 경우는 해당 수까지가 한수의 개수
	if (n < 100)
		cnt = n;
	else { // n이 100이상인 경우 100이상에 해당하는 수를 전체 탐색

		for (int i = 100; i <= n; i++) {
			arr[0] = i / 100; // 100의 자리 수
			arr[1] = (i % 100) / 10;  // 10의 자리 수
			arr[2] = (i % 100) % 10;  // 1의 자리 수

			// 공차가 존재하는 등비수열일 경우 한수에 해당
			if ((arr[0] - arr[1]) ==( arr[1] - arr[2]))
				cnt++;
		}
	}

	printf("%d\n", cnt);

	return 0;
}