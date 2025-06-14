// Strict Lists

let l = 1::2::nil;
match l {
    nil -> println(nil)
|   x::y -> println(x); println(y)
};;
// 1 
// Lista
// 0

let len : list<int>->int = fn l : list<int> => {   
    match l {
        nil -> 0
        | x::y -> 1 + len (y)
    }
};
let l0 = 1::2::3::nil;
len (l0);;
// 3

let mkl: int->list<int> = fn n : int =>
{
    if (n==0) {
        nil
    } else {
        n::( mkl(n-1))
    }
};
let len: list<int>->int = fn l : list<int> => {
    match l {
        nil -> 0
        | x::y -> 1 + len (y)
    }
};
let l100 = mkl (100);
println l100;
len(l100)
;;
// Lista: 100
// 100

let mkl: int->list<int> = fn n : int =>
{
    if (n==0) {
        nil
    } else {
        n::( mkl(n-1))
    }
};
let suml: list<int>->int = fn l : list<int> =>
    {   
        match l {
        nil -> 0
      | x::y -> x + suml (y)
    }};
let l100 = mkl (100);
println l100;
suml(l100)
;;
// Lista: 100
// 5050

// Lazy Lists

let l = 1:?2:?nil;
match l {
    nil -> println(nil)
|   x::y -> println(x); println(y)
};;
// 1
// LazyList
// 0

let l = 1:?2::nil;
match l {
    nil -> println(nil)
|   x::y -> println(x); println(y)
};;
// 1
// Lista 2
// 0

let add1: list<int>->list<int> = fn l : list<int> => {
    match l {
        nil -> nil
        |
        h::t -> (h+1)::(add1 (t))
    }
};
let mkl: int->list<int> = fn n : int =>
{
    if (n==0) {
        nil
    } else {
        n::( mkl(n-1))
    }
};
add1 ( mkl (10) ) ;;
// Lista 11

let add1: list<int>->list<int> = fn l : list<int> => {
    match l {
        nil -> nil
        |
        h::t -> (h+1)::(add1 (t))
    }
};
let mkll: int->list<int> = fn n : int =>
{
    if (n==0) {
        nil
    } else {
        n:?( mkll(n-1))
    }
};
let ll100 = mkll (100);
add1 ( ll100 ) ;;
// Lista 101

let intsfm: int->list<int> = fn n : int => {
    n:? (intsfm (n+1))
};
let pfst: list<int>->int->int = fn l : list<int>,n : int => {
    if (n==0) {
        println(n)
    } else {
        match l {
            nil -> 0
            |
            h::t -> println(h); pfst (t) (n-1)
        }
    }
};
let l = intsfm (0);
pfst (l) (20);;
// 0 - 19
// 0
// 0

let fibo: int->int->list<int> = fn a : int, b : int => { a :? (fibo (b) (a+b)) };
let fibogen = fibo (0) (1);
let count = box ( 30 ) ;
let lv = box( fibogen );
while (!count != 0) {
     match (!lv) {
        nil -> println (0); nil // added nil here so match gives same output ask teacher
      | v :: tail -> println (v); lv := tail
     };
     count := !count - 1
};;       
// 30 fibonacci numbers
// false

let intsfm: int->list<int> = fn n : int => {
    n:? (intsfm (n+1))
};
let filter: list<int>->(int->bool)->list<int> = fn l : list<int>, g : int->bool => {
    match l {
        nil -> nil
    |
        h::t -> if (g(h)) {
            h :? (filter (t) (g))
        } else { filter (t) (g)}
    }
};
let pfst: list<int>->int->int = fn l : list<int>,n : int => {
    if (n==0) {
        println(n)
    } else {
        match l {
            nil -> 0
            |
            h::t -> println(h); pfst (t) (n-1)
        }
    }
};
let l = intsfm (0);
let even = fn n : int => { (n/2)*2 == n };
let m = filter (l) (even);
pfst (m) (100);;
// multiplos de 2 ate 198
// 0
// 0
