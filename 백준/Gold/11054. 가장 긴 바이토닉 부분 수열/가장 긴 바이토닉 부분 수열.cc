//11054
#include <iostream>
#include <algorithm>

using namespace std;

int dp[2][1001];
int a[1001];

int n;

int main() {
	int MAX;

	cin >> n;

	for (int i = 1; i <= n; i++) {
		cin >> a[i];
	}

	for (int i = 1; i <= n; i++) {
		MAX = 0;

		for (int j = i; j >= 0; j--) {
			if (a[j] < a[i]) {
				MAX = max(MAX, dp[0][j]);
			}
		}

		dp[0][i] = MAX + 1;
	}

	for (int i = n; i >= 0; i--) {
		MAX = 0;

		for (int j = 1; j <= n; j++) {
			if (a[j] < a[i]) {
				MAX = max(MAX, dp[1][j]);
			}
		}

		dp[1][i] = MAX + 1;
	}

	MAX = 0;

	for (int i = 0; i <= n; i++) {
		MAX = max(MAX, dp[0][i] + dp[1][i]);
	}

	cout << MAX - 1 << endl;

	return 0;
}