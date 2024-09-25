// 문자열 안에서 특정 문자를 찾아내는 도구
var pattern = /a/;
var pattern = new RegExp('a');
// '/'로 감싸진 부분이 패턴이다.

// exec, 문자열 검색
console.log(pattern.exec('abcdef')); // ["a"]
console.log(pattern.exec('bcdefg')); // null

// test, 문자열에 해당 패턴이 있는지 검사
console.log(pattern.test('abcdef')); // true
console.log(pattern.test('bcdefg')); // false

// 문자열 객체의 메소드
// match, 문자열 검색
console.log('abcdef'.match(pattern)); // ["a"]
console.log('bcdefg'.match(pattern)); // null

// replace, 문자열 치환
console.log('abcdef'.replace(pattern, 'A')); // Abcdef

// 정규표현식의 옵션
// i, 대소문자 구분 없이 검색
var xi = /a/;
console.log('Abcde'.match(xi)); // null
var oi = /a/i;
console.log('Abcde'.match(oi)); // ["A"]

// g, 패턴과 일치하는 모든 문자열을 검색
var xg = /a/;
console.log('abcdea'.match(xg)); // ["a"]
var og = /a/g;
console.log('abcdea'.match(og)); // ["a", "a"]