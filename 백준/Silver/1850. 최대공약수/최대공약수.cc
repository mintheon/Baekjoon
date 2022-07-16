#include <iostream>

using namespace std;

int get_gcd(long long a, long long b) {
	if (a % b == 0) {
		return b;
	}
	
	return get_gcd(b, a % b);
}

int main() {
	long long a, b;

	cin >> a >> b;

	int result = get_gcd(a, b);

	for (int i = 0; i < result; i++) {
		cout << "1";
	}

	cout << "\n";

	return 0;
}