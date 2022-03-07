#include <iostream>
#include <vector>

using namespace std;

int main() {
	int n, k;

	cin >> n >> k;

	vector<int> l;
	for (int i = 0; i < n; i++) {
		l.push_back(i + 1);
	}

	vector<int>::iterator p = l.begin();

	cout << "<";

	for (int i = 0; i < n - 1; i++) {
		for (int j = 0; j < k - 1; j++) {
			p++;
			if (p == l.end()) {
				p = l.begin();
			}
		}
		cout << *p << ", ";
		p = l.erase(p);

		if (p == l.end()) {
			p = l.begin();
		}
	}

	cout << l.front() << ">\n";

	return 0;
}