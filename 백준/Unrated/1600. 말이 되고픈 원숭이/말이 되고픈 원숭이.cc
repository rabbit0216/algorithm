#include <iostream>
#include <queue>
using namespace std;

struct Monkey {
	int r, c;
	int jump;
	int dist;
};


int R, C, K, ans;
int graph[201][201];
bool visited[201][201][31];
queue<Monkey> q;
int monkeyDeltas[4][2] = { {0,1},{1,0},{0,-1},{-1,0} };
int horseDeltas[8][2] = { {2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {-1, 2},
			{1, -2}, {-1, -2} };
void bfs();
bool scope(int, int);

int main() {

	cin >> K;
	cin >> C >> R;

	for (int r = 0; r < R; r++)
	{
		for (int c = 0; c < C; c++)
		{
			cin >> graph[r][c];
		}
	}

	Monkey monkey = { 0,0,K,0 };
	q.push(monkey);
	visited[0][0][0] = true;
	ans = -1;
	bfs();
	cout << ans << '\n';
	return 0;
}

void bfs() {
	while (!q.empty()) {
		Monkey cur = q.front(); q.pop();
		
		if (cur.r == R - 1 && cur.c == C - 1) {
			ans = cur.dist;
			return;
		}

		if (cur.jump > 0) {
			for (int dir = 0; dir < 8; dir++)
			{
				int nr = cur.r + horseDeltas[dir][1];
				int nc = cur.c + horseDeltas[dir][0];
				if (!scope(nr, nc)) continue;
				if (!visited[nr][nc][cur.jump - 1] && graph[nr][nc] != 1) {
					visited[nr][nc][cur.jump - 1] = true;
					Monkey next = { nr,nc,cur.jump - 1,cur.dist + 1 };
					q.push(next);
				}
			}
		}

		for (int dir = 0; dir < 4; dir++)
		{
			int nr = cur.r + monkeyDeltas[dir][1];
			int nc = cur.c + monkeyDeltas[dir][0];

			if (!scope(nr, nc)) continue;
			if (!visited[nr][nc][cur.jump] && graph[nr][nc] != 1) {
				visited[nr][nc][cur.jump] = true;
				Monkey next = { nr,nc,cur.jump,cur.dist + 1 };
				q.push(next);
			}		
		}
	}
}

bool scope(int r, int c) {
	return r >= 0 && r < R&& c >= 0 && c < C;
}