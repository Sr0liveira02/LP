let m = box(9);
let v = !m;
v+1;;
// 10

let m = box(0);
m := !m + 1;
m := !m + 1;
m := !m + 1;
m := !m + 1;
m := !m + 1;
!m
;;
// 5

let m = box(9);
let v = !m;
m := v + 1;
v
;;
// 9

let m = box(9);
let v = !m;
m := v + 1;
!m
;;
// 10

let c = 0;
let L = 1000;
let m = box(c);
while (!m>0) {
    m := !m - 1
};
!m
;;
// 0

let c = 0;
let L = 1000;
let m = box(L);
let S = box(c);
while (!m>0) {
    m := !m - 1;
    S := !S + !m
};
!S
;;
//499500

// let L = 1000;
// let m = box(L);
// let fnxt = box(1);
// while (!m>0) {
//    let t = !fnxt;
//    fp := t;
//    fnxt := !fnxt + !fp;
//    m := !m - 1
// };
// !fp
// ;;
// e dat let a fp sua puta


let sigfpe = box ( fn x: int => {x} );
let setfpe = fn h : int->int => { sigfpe := h };
let div = fn n : int,m : int => {
      if (m==0) { (!sigfpe) (n) }
        else { n / m}
};
setfpe (fn v : int => { -1 });
div (2) (0)
;;
// -1

let knot = box (fn x : int => {x});
let fact = fn n: int => {
      if (n==0) { 1 }
        else { n * ((!knot)( n - 1 ))}
};
knot := fact;
fact (6)
;;
// 720

let mkpair =
    fn a : int,b : int => { 
        let l = box(a);
        let r = box(b);
        fn f: ref<int>->ref<int>->int => { f (l) (r) }
};
let getfst = fn p: (ref<int>->ref<int>->int)->int => { p (fn a: ref<int>,b : ref<int> => {!a }) };
let setfst = fn p: (ref<int>->ref<int>->int)->int, v: int => { p (fn a: ref<int>,b: ref<int> => {a := v })};
let getsnd = fn p: (ref<int>->ref<int>->int)->int => {p (fn a: ref<int>,b: ref<int> => {!b })};
let setsnd = fn p: (ref<int>->ref<int>->int)->int, v: int => { p (fn a: ref<int>,b: ref<int> => {b := v})};
let x = mkpair (1) (2);
setfst (x) (10);
setsnd (x) (20);
(getfst (x)) + (getsnd (x))
;;
// 30

let mkpair =
    fn a: int, b: int => { 
        let l = box(a);
        let r = box(b);
        fn f: ref<int>->ref<int>->int => { f (l) (r) }
};
let getfst = fn p: (ref<int>->ref<int>->int)->int => { p (fn a: ref<int>,b: ref<int> => {!a }) };
let setfst = fn p: (ref<int>->ref<int>->int)->int,v: int => { p (fn a: ref<int>,b: ref<int> => {a := v })};
let getsnd = fn p: (ref<int>->ref<int>->int)->int => {p (fn a: ref<int>,b: ref<int> => {!b })};
let setsnd = fn p: (ref<int>->ref<int>->int)->int,v: int => { p (fn a: ref<int>,b: ref<int> => {b := v})};
let mp = mkpair(2)(3);
setfst(mp) (getfst(mp)+getsnd(mp));
getsnd(mp)* (getfst(mp));;
// 15

