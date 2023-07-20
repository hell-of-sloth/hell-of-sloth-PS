a = [];

// 배열의 크기
a.length;

// 원소 추가
a.push('zero');

// 배열로 뒤에 잇기
a.concat(['one', 'two']);

// 배열 시작점에 원소 추가
a.unshift('minus one');

// 배열 스플라이스
a.splice(1, 0, 'one and a half'); // 1번째 인덱스에서 0개를 지우고 'one and a half'를 추가
                                 // 인덱스 앞에 추가

// 배열의 원소 삭제
a.splice(2, 1); // 2번째 인덱스에서 1개를 지움
a.shift(); // 첫번째 원소를 지움
a.pop(); // 마지막 원소를 지움

// 정렬
a.sort(); // 사전순으로 정렬
a.reverse(); // 역순으로 정렬