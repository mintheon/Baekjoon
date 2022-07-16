#include <iostream>
#include <vector>
#include <algorithm>

using namespace std;

int main() {
	int n;

	cin >> n;

	vector<int> dp(n + 1, 0);

	for (int i = 1; i <= n; i++) {
		int temp = 100001;

		for (int j = 1; j * j <= i; j++) {
			temp = min(temp, dp[i - (j*j)]);
		}

		dp[i] = temp + 1;
	}
	
	cout << dp[n] << endl;

	return 0;
}