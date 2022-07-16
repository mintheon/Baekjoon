//1924
#include <iostream>
#include <vector>
#include <string>

using namespace std;

int main() {
	vector<string> day_name = { "SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
	vector<int> mon_num = { 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31 };

	int mon, day;

	cin >> mon >> day;

	for (int i = 0; i < mon - 1; i++) {
		day = day + mon_num[i];
	}

	cout << day_name[day % 7] << endl;

	return 0;
}