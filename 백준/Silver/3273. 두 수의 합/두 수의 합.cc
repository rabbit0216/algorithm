#include <iostream>
#include <vector>
#include <algorithm>
using namespace std;

int main() {
	int n, x;
	int cnt = 0;
	cin >> n;
	
	vector<int> v;
	for (int i = 0; i < n; i++) {
		cin >> x;
		v.push_back(x);
	}
	sort(v.begin(), v.end());
	cin >> x;
	int start = 0, end = n - 1;
	while (start < end) {
		if (v[start] + v[end] == x) {
			cnt++;
			end--;
		}
		else if (v[start] + v[end] > x)
			end--;
		else
			start++;
		
	}
	cout << cnt;
	return 0;
}
