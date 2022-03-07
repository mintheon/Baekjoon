#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {
	vector<string> arr = { "000", "001", "010", "011", "100", "101", "110", "111" };

	string s;
	string s_temp;
	cin >> s;

	for (int i = 0; i < s.length(); i++) {
		s_temp += arr[s[i] - '0'];
	}

	if (s_temp[0] == '0') {
		if (s_temp[1] == '0') {
			s_temp = s_temp.substr(2, s_temp.length() - 2);
		}
		else {
			s_temp = s_temp.substr(1, s_temp.length() - 1);
		}
	}

	cout << s_temp << endl;

	return 0;
}