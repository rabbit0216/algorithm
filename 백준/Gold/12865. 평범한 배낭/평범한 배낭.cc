//다시
#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;
int n, k;
int d[1000001];
int w[101];
int v[101];
void dp() {
	for (int i = 1;i <= n;i++) {
		for (int j = k;j >= 1;j--) {
			if (w[i] <= j) {
				d[j] = max(d[j], d[j - w[i]] + v[i]);
			}
		}
	}
	cout << d[k];
}
int main() {
	cin >> n >> k;
	for (int i = 1;i <= n;i++) {
		cin >> w[i] >> v[i];
	}
	dp();
	return 0;
}