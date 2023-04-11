#include <iostream>
#include <vector>
#include <cmath>
#include <string>
#define MAX 236200
using namespace std;

/*
1. 숫자 방문 처리 하면서 돌기
2. 방문 한 숫자 나오면 해당 숫자 저장
3. 저장된 방문한 숫자 이후에 나온 값들은 카운팅 x
*/

string A;
int P;
bool visited[MAX];
int split[7];
vector<int> nums;

int main() {
	cin >> A >> P;

	for (int i = 0; i < A.length(); i++) {
		split[i] = A[i] - '0';
	}
	visited[stoi(A)] = true;
	nums.push_back(stoi(A));

	int redundant;

	while (true) {
		int num = 0;
		for (int i = 0; i < 7; i++) {
			num += (int)pow(split[i], P);
		}

		if (visited[num]) {
			redundant = num;
			break;
		}

		visited[num] = true;
		nums.push_back(num);
		A = to_string(num);

		for (int i = 0; i < 7; i++) {
			if (i >= A.length()) split[i] = 0;
			else split[i] = A[i] - '0';
		}
	}
	
	int ans = 0;
	for (int i = 0; i < nums.size(); i++)
	{
		if (nums[i] == redundant) {
			ans = i;
			break;
		}
	}
	cout << ans << '\n';
	return 0;
}