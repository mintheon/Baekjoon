#include <iostream>
#include <string>

using namespace std;

int main() {
	string s = "00";
	string s_temp;
	cin >> s_temp;
	s.append(s_temp);

	int len = s.length();

	for (int i = len % 3; i < len; i += 3) {
		cout << ((s[i] - '0') * 4) + ((s[i+1] - '0') * 2) + (s[i+2] - '0');
	}

	return 0;
}

