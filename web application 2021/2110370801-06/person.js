var person1 = {
    name: "Kindai",
    point: 100,
    sayName: function() {  // 匿名関数でメソッド定義
      alert(this.name);
    },
    addPoint: function() {  // 匿名関数でメソッド定義
      this.point +=1;
    }
  };
      
  person1.sayName();          // alert窓にnameプロパティを出力
  displayPoint(person1);      // 関数宣言は巻き戻して呼び出せる
  person1.addPoint();
  displayPoint(person1);      // pointプロパティが1増えている
  
  function displayPoint(p) {  // 関数宣言
    alert(p.point);
  }
  