#include <iostream>
#include <vector>

using namespace std;

int main() {
	int m, n;
	vector<int> v;

	cin >> m >> n;

	v.resize(n + 1);

	for (int i = 0; i < v.size(); i++) {
		v[i] = true;
	}

	v[1] = false;

	for (int i = 2; i <= n; i++) {
		for (int j = 2; i * j <= n; j++) {
			v[i * j] = false;
		}
	}

	for (int i = m; i <= n; i++) {
		if (v[i] == true) {
			cout << i << "\n";
		}
	}

	return 0;
}