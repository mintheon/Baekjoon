#include <iostream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

int main() {
	int N, M;
	int xiao = 64;
	int B_s = 0, W_s = 0;
	string text[50];

	cin >> N >> M;
	cin.get();

	for (int i = 0; i < N; i++) {
		cin >> text[i];
	}

	for (int i = 0; i <= N - 8; i++) {
		for (int l = 0; l <= M - 8; l++) {
			for (int j = i; j < (i + 8); j++) {
				for (int k = l; k < (l + 8); k++) {
					if (j % 2 == 0) {
						if (k % 2 == 0) {
							if (text[j][k] == 'W') {
								B_s++;
							}
							else {
								W_s++;
							}
						}
						else {
							if (text[j][k] == 'B') {
								B_s++;
							}
							else {
								W_s++;
							}
						}
					}
					else {
						if (k % 2 == 0) {
							if (text[j][k] == 'W') {
								W_s++;
							}
							else {
								B_s++;
							}
						}
						else {
							if (text[j][k] == 'B') {
								W_s++;
							}
							else {
								B_s++;
							}
						}
					}
				}
			}

			xiao = min(xiao, B_s);
			xiao = min(xiao, W_s);

			W_s = 0; B_s = 0;
		}
	}

	cout << xiao << endl;

	return 0;
}