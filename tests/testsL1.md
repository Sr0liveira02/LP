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


let sigfpe = box ( fn x=>{x} );
let setfpe = fn h => { sigfpe := h };
let div = fn n,m => {
      if (m==0) { (!sigfpe) (n) }
        else { n / m}
};
setfpe (fn v => { -1 });
div (2) (0)
;;
// -1

let knot = box (fn x => {x});
let fact = fn n => {
      if (n==0) { 1}
        else { n * ((!knot)( n - 1 ))}};
knot := fact;
fact (6)
;;
// 720

let mkpair =
    fn a,b => { 
        let l = box(a);
        let r = box(b);
        fn f => { f (l) (r) }
};
let getfst = fn p => { p (fn a,b => {!a }) };
let setfst = fn p,v => { p (fn a,b => {a := v })};
let getsnd = fn p => {p (fn a,b => {!b })};
let setsnd = fn p,v => { p (fn a,b => {b := v})};
let x = mkpair (1) (2);
setfst (x) (10);
setsnd (x) (20);
(getfst (x)) + (getsnd (x))
;;
// 30

let mkpair =
    fn a,b => { 
        let l = box(a);
        let r = box(b);
        fn f => { f (l) (r) }
};
let getfst = fn p => { p (fn a,b => {!a }) };
let setfst = fn p,v => { p (fn a,b => {a := v })};
let getsnd = fn p => {p (fn a,b => {!b })};
let setsnd = fn p,v => { p (fn a,b => {b := v})};
let mp = mkpair(2)(3);
setfst(mp) (getfst(mp)+getsnd(mp));
getsnd(mp)* (getfst(mp));;
// 15

