import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static StringTokenizer stringTokenizer;
    static Deque<Long> numberDQ = new ArrayDeque<>();
    static Deque<Character> opeDQ = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {
        // 입력 처리
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer stringTokenizer = new StringTokenizer(bufferedReader.readLine(), "\\+|-|\\*|/", true);

        String temp = stringTokenizer.nextToken();
        if (temp.equals("-")) {
            numberDQ.add(Long.parseLong(temp.concat(stringTokenizer.nextToken())));
        } else {
            numberDQ.add(Long.parseLong(temp));
        }
        while (stringTokenizer.hasMoreElements()) {
            opeDQ.add(stringTokenizer.nextToken().charAt(0));
            numberDQ.add(Long.parseLong(stringTokenizer.nextToken()));
        }


        long result = 0;
        while (!numberDQ.isEmpty()) {
            if (numberDQ.size() == 1) {
                result = numberDQ.poll();
                break;
            }
            if (numberDQ.size() >= 4) {
                long frontFirst = numberDQ.pollFirst();
                long frontSecond = numberDQ.pollFirst();
                char frontOpe = opeDQ.pollFirst();

                long backSecond = numberDQ.pollLast();
                long backFirst = numberDQ.pollLast();
                char backOpe = opeDQ.pollLast();

                int who = whoFirst(frontFirst, frontSecond, frontOpe, backFirst, backSecond, backOpe);
                if (who == 1) {
                    result = calc(frontOpe, frontFirst, frontSecond);
                    numberDQ.addFirst(result);
                    numberDQ.addLast(backFirst);
                    numberDQ.addLast(backSecond);
                    opeDQ.addLast(backOpe);
                } else if(who == -1){
                    result = calc(backOpe, backFirst, backSecond);
                    numberDQ.addLast(result);
                    numberDQ.addFirst(frontSecond);
                    numberDQ.addFirst(frontFirst);
                    opeDQ.addFirst(frontOpe);
                }
            } else if (numberDQ.size() >= 3) {
                long first = numberDQ.pollFirst();
                long second = numberDQ.pollFirst();
                long third = numberDQ.pollFirst();
                char frontOpe = opeDQ.pollFirst();
                char backOpe = opeDQ.pollLast();

                if ((frontOpe == backOpe) || (frontOpe == '*' && backOpe == '/') || (frontOpe == '/' && backOpe == '*')
                        || (frontOpe == '+' && backOpe == '-') || (frontOpe == '-' && backOpe == '+')) {
                    //연산 결과 더 큰쪽
                    long frontResult = calc(frontOpe, first, second);
                    long backResult = calc(backOpe, second, third);
                    if (frontResult >= backResult) {
                        numberDQ.addFirst(frontResult);
                        numberDQ.addLast(third);
                        opeDQ.add(backOpe);
                    } else {
                        numberDQ.addLast(backResult);
                        numberDQ.addFirst(first);
                        opeDQ.add(frontOpe);
                    }
                }

                if ((frontOpe == '*' || frontOpe == '/') && (backOpe == '+' || backOpe == '-')) {
                    //front 먼저 계산
                    long frontResult = calc(frontOpe, first, second);
                    numberDQ.addFirst(frontResult);
                    numberDQ.addLast(third);
                    opeDQ.add(backOpe);
                }

                if ((frontOpe == '+' || frontOpe == '-') && (backOpe == '*' || backOpe == '/')) {
                    long backResult = calc(backOpe, second, third);
                    numberDQ.addLast(backResult);
                    numberDQ.addFirst(first);
                    opeDQ.add(frontOpe);
                }

            } else {
                long first = numberDQ.pollFirst();
                long second = numberDQ.pollLast();
                char ope = opeDQ.poll();
                result = calc(ope, first, second);
                numberDQ.add(result);
            }
        }
        System.out.println(result);
    }

    public static int whoFirst(long frontFirst, long frontSecond, char frontOpe,
                               long backFirst, long backSecond, char backOpe) {
        //1:front -1:back
        if ((frontOpe == backOpe) || (frontOpe == '*' && backOpe == '/') || (frontOpe == '/' && backOpe == '*')
                || (frontOpe == '+' && backOpe == '-') || (frontOpe == '-' && backOpe == '+')) {
            //같은경우 연산 결과 더 큰쪽
            long frontResult = calc(frontOpe, frontFirst, frontSecond);
            long backResult = calc(backOpe, backFirst, backSecond);
            if (frontResult >= backResult) {
                return 1;
            } else {
                return -1;
            }
        }
        if ((frontOpe == '*' || frontOpe == '/') && (backOpe == '+' || backOpe == '-')) {
            //front 먼저 계산
            return 1;
        }
        if ((frontOpe == '+' || frontOpe == '-') && (backOpe == '*' || backOpe == '/')) {
            //back 먼저
            return -1;
        }
        return 0;
    }


    public static long calc(char ope, long x, long y) {
        switch (ope) {
            case '+':
                return x + y;
            case '-':
                return x - y;
            case '*':
                return x * y;
            case '/':
                return x / y;
        }
        return 0;
    }
}