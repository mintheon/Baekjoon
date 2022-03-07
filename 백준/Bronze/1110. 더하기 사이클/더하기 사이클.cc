//01110
#include <iostream>

using namespace std;

int main() {
	int n;
	int temp, result = 0, cnt = 0;

	cin >> n;

	temp = n;

	do {
		result = (temp / 10) + (temp % 10);
		result = (result % 10) + ((temp % 10) * 10);

		temp = result;
		cnt++;
	} while (n != result);

	cout << cnt << endl;

	return 0;
}