//01436
#include <iostream>
#include <string>
using namespace std;
int main()
{
	int n, num = 0;
	int i = 666;
	string s;

	cin >> n;

	while (true) {
		s = to_string(i);

		if (s.find("666") != -1) {
			num++;
		}
			
		if (num == n)
		{
			cout << i << endl;
			break;
		}

		i++;
	}
}