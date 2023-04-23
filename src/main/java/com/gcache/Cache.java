
/*
put
evict
conditions
timeouts
limits



addCacheEntry(items...)
evict(

numberOfHits --> number of times used

{
numberOfHits: "karnataka"
"karnataka" : numberOfHits,

}
[
"karnataka": {
item: "bangalore",
numberOfHits++:_____ min
},
"ap"
]

{
k:1, p:2
}



{
a:3, t:1, k:2
}



[null, t, k, a]




[n, a1,b,c,k, p, n, n,n ,n ,n ,n]
    f       r

["k", p]
*/


package com.gcache;

import java.util.List;

public interface Cache {

    public <V> void insert(String key, V value);

    public <V> V fetch(String key);

    public List<Object> fetchAll();
    
    public int count();

};




