var mod = {
    mod: function(id){
        id %=50;
        return id;
    }
  };

  displayPoint(mod);      // pointプロパティが1増えている
  
  function displayPoint(p) {  // 関数宣言
    alert(p.mod(2110370801));
}
  