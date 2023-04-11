#include <iostream>
using namespace std;
#define MAX 1010

/*
#union-find
*/

int N;
int repres[1010];
int org[1010];
int ans;

int findSet(int v);
bool unionNode(int a, int b);

int main() {
	int T;
	cin >> T;
	while (T--) {
		ans = 0;

		cin >> N;

		for (int i = 1; i <= N; i++)
		{
			repres[i] = i;
		}

		for (int i = 1; i <= N; i++) {
			int to;
			cin >> to;
			if (!unionNode(i, to)) {
				ans++;
			}
		}
		cout << ans << '\n';
	}
	return 0;
}

int findSet(int v) {
	if (v == repres[v]) return v;
	return repres[v] = findSet(repres[v]);
}

bool unionNode(int a, int b) {
	int rootA = findSet(a);
	int rootB = findSet(b);
	
	if (rootA == rootB) return false;
	else {
		repres[rootA] = rootB;
		return true;
	}
}