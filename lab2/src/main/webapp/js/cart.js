const items = document.querySelector("#items"),
  shoppingCartContent = document.querySelector("#cart-content tbody");

  loadEventListeners();

function loadEventListeners(){
    //when new item is added
    items.addEventListener('click', buyItems);

function buyItems(e){
    if (e.target.classList.contains('card-front')){
        //read the item value
        console.log("click!");
        const item = e.target.parentElement.parentElement;
        getItemInfo(item);
    }
}}