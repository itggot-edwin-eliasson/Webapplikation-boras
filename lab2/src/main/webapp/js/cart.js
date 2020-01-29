$(document).ready(function() {
    shoppingCartContent = document.querySelector("#cart-content tbody");
    $(".card").click(function() {
        getItemInfo(this);
    });
});

function getItemInfo(item) {
    const itemInfo = {
        image: item.querySelector("img").src,
        title: item.querySelector("h5").textContent,
        price: item.querySelector("h6").textContent
    };
    addToCart(itemInfo);
}
function addToCart(item) {
    const row = document.createElement('tr');
    row.innerHTML = `
        <tr class="item">
            <td>
                <img src="${item.image}" width="100">
            </td>
            <td>
                ${item.title}
            </td>
            <td class="price">
                ${item.price}
            </td>
        </tr>
        `
            ;
    shoppingCartContent.appendChild(row);
    updatePrice();
}
function updatePrice(){
    totalPrice = document.querySelector("#total-price");
    prices = document.querySelectorAll(".price");
    var sum = 0;
    for(let price of prices){
        sum += parseInt(price.textContent.substring(0, price.textContent.length - 1));
    }
    totalPrice.textContent = "Total price: " + sum + "$";
}

