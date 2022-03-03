#include <iostream>
#include <vector>

const long long MOD = 1000000000;

using namespace std;

int main() {
	int n, k;

	cin >> n >> k;

	vector<vector<long long> > dp(k + 1, vector<long long>(n + 1, 0));

	for (int i = 1; i <= k; i++) {
		dp[i][0] = 1;
	}

	for (int i = 1; i <= k; i++) {
		for (int j = 1; j <= n; j++) {
			dp[i][j] = (dp[i - 1][j] + dp[i][j - 1]) % MOD;
		}
	}

	cout << dp[k][n] << endl;

	return 0;
}