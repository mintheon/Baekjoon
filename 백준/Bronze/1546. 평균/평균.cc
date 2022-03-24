#include <iostream>
#include <vector>

using namespace std;

int main() {
	double n;
	int input;
	vector <double> score;
	double max = -1;
	double sum = 0;

	cin >> n;
	
	for (int i = 0; i < n; i++) {
		cin >> input;
		score.push_back(input);
	}

	for (int i = 0; i < n; i++) {
		if (score[i] > max) {
			max = score[i];
		}
	}

	for (int i = 0; i < n; i++) {
		score[i] = score[i] / max * 100;
		sum = sum + score[i];
	}

	cout << sum / n << endl;

	return 0;
}