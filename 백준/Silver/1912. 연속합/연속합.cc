//1912
#include <iostream>
#include <algorithm>

using namespace std;

int n;
int dp[100010];

int main() {
	int MAX, sum = 0;

	cin >> n;

	for (int i = 1; i <= n; i++) {
		cin >> dp[i];
	}

	MAX = dp[1];

	for (int i = 2; i <= n; i++) {
		if (dp[i - 1] > 0 && dp[i] + dp[i - 1] > 0) {
			dp[i] += dp[i - 1];
		}

		MAX = max(MAX, dp[i]);
	}

	cout << MAX << endl;

	return 0;
}