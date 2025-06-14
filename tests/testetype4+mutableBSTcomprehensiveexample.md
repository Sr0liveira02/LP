type NODE = struct { #val:int, #next:LIST };
type LIST = union { #nil: (), #cons: NODE };
let l0:LIST = #nil (()) ;
let l1:LIST = #cons ( { #val=2, #next=l0}) ;
let l2:LIST = #cons ( { #val=3, #next=l1}) ;
l1;;

type NODE = struct {
        #val:int,
        #next:LIST
};
type LIST = union {
        #nil: (),
        #cons: NODE
};
let l0:LIST = #nil (()) ;
let l1:LIST = #cons ( { #val=2, #next=l0}) ;
let l2:LIST = #cons ( { #val=3, #next=l1}) ;
let plist:LIST->() = fn
    l:LIST => { 
        match l {
            #nil(_) -> println (-1000); ()
        |   #cons(n) -> 
                print (n.#val);
                plist (n.#next)
        }
    }
;
plist (l2);;

type NODE = struct {
        #val:int,
        #next:LIST
};

type LIST = union {
        #nil: (),
        #cons: NODE
};

let cons: int -> LIST -> LIST =
    fn v:int, l:LIST =>
        {#cons ( {#val=v, #next=l})};

let concat:LIST->LIST->LIST =
    fn a:LIST, b:LIST => { 
        match a {
            #nil(_) -> b
        |   #cons(n) -> 
                cons (n.#val) (concat (n.#next) (b))
        }
    };

let genlist: int -> LIST =
    fn n:int => {
        if (n==0) { #nil(()) }
        else {
            cons (n) (genlist (n-1))
        }
    };

let print_list:LIST->() = fn
    l:LIST => { 
        match l {
            #nil(_) -> ()
        |   #cons(n) -> 
                println (n.#val);
                print_list (n.#next)
        }
    }
;

let l0 = genlist (100);
let la = concat (l0) (l0);
print_list (la);;

// mutable binary search tree  */

type TNODE = struct {
        #val:int,
        #left:ref<BTREE>,
        #right:ref<BTREE>
};

type BTREE = union {
        #nil: (),
        #node: TNODE
};

type RTREE = ref<BTREE>;

let mknil: BTREE = #nil(());

let mknode:int->BTREE =
    fn n:int => {
        #node({#val = n,
               #left = box(mknil), 
               #right = box(mknil)})
    };

let insert: RTREE->int->() = 
    fn t:RTREE, n:int =>
    {
        match !t {
            #nil(_) -> 
                t := mknode (n); ()
        |   #node(c) ->
            if (n<c.#val)
                { insert (c.#left) (n) }
                else { insert (c.#right) (n) }
        }
    };

let inorder: RTREE->() = 
    fn t:RTREE =>
    {
        match !t {
            #nil(_) ->  ()
        |   #node(c) ->
            inorder (c.#left);
            println(c.#val);
            inorder (c.#right)
        }
    };

let tree:RTREE = box(mknil);
insert (tree) (5);
insert (tree) (3);
insert (tree) (10);
insert (tree) (8);
insert (tree) (7);
insert (tree) (2);
insert (tree) (9);
inorder (tree)
;;

// mutable binary search tree with iterator */

type TNODE = struct {
        #val:int,
        #left:ref<BTREE>,
        #right:ref<BTREE>
};

type BTREE = union {
        #nil: (),
        #node: TNODE
};

type RTREE = ref<BTREE>;

let mknil: BTREE = #nil(());

let mknode:int->BTREE =
    fn n:int => {
        #node({#val = n,
               #left = box(mknil), 
               #right = box(mknil)})
    };

let insert: RTREE->int->() = 
    fn t:RTREE, n:int =>
    {
        match !t {
            #nil(_) -> 
                t := mknode (n); ()
        |   #node(c) ->
            if (n<c.#val)
                { insert (c.#left) (n) }
                else { insert (c.#right) (n) }
        }
    };

let treeiterator: RTREE->(int->())->() = 
    fn t:RTREE,f:int->() =>
    {
        match !t {
            #nil(_) ->  ()
        |   #node(c) ->
            treeiterator (c.#left) (f);
            f (c.#val);
            treeiterator (c.#right) (f)
        }
    };

let tree:RTREE = box(mknil);
insert (tree) (5);
insert (tree) (3);
insert (tree) (10);
insert (tree) (8);
insert (tree) (7);
insert (tree) (2);
insert (tree) (9);
treeiterator (tree) (fn v:int => { println(v); ()} )
;;








