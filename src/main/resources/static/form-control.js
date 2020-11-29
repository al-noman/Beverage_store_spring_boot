$(document).on("click", ".create-button", function (){
    $(".dynamic-content").hide();
    let $form = $(".form-page").clone();
    $form.addClass("dynamic-form");
    $form.removeClass("form-page")
    $(".container").append($form);
    $form.find("form").empty();
    $form.show();

    let $resource = $(this).attr("data-resource");
    if ($resource == "beverages"){
        $form.find(".form-title").text("New Beverage");
        createFormField($form, "text", "Beverage Name", "beverage_name");
        createFormField($form, "text", "Beverage Manufacturer", "beverage_manufacturer");
        createFormField($form, "number", "Quantity", "beverage_quantity");
        createFormField($form, "number", "Price", "beverage_price");
        // let $selectField = $form.find(".input-select").clone();
        createCancelSubmitButtons($form, $resource);
    }
    else if ($resource == "incentives"){
        $form.find(".form-title").text("New Incentive");
        createFormField($form, "text", "Incentive Name", "incentive_name");
        createFormField($form, "radio", "Incentive type", "incentive_type");
        createCancelSubmitButtons($form, $resource);
    }
});

$(document).on("click", ".cancel-button", function (){
    $(".dynamic-content").show();
    $(".dynamic-form").remove();
});

$(document).on("click", ".submit-button", function (e){
    e.preventDefault();
    $resource = $(this).data("resource")

    if ($resource == "beverages"){
        let $name = $('input[name="beverage_name"]').val();
        let $manufacturer = $('input[name="beverage_manufacturer"]').val();
        let $quantity = $('input[name="beverage_quantity"]').val();
        let $price = $('input[name="beverage_price"]').val();
        let $beverage = {
            name: $name,
            manufacturer: $manufacturer,
            quantity: $quantity,
            price: $price
        }
        console.log($beverage);
        postFormData($resource, $beverage, postMethodCallback);
    }
    else if ($resource == "incentives"){
        let $name = $('input[name="incentive_name"]').val();
        let $incentive_type = "trial_package";
        $incentive_type = $('input[name="incentive_type"]:checked').val();
        let $incentive = {
            name: $name,
            incentive_type: $incentive_type
        }
        console.log($incentive);
        postFormData($resource, $incentive, postMethodCallback);
    }
});

$(document).on("click", ".order-button", function (){
    let $customerOrder = [];
    let $checkedBeverages = $("input[name='selectedBeverage']:checked");
    if ($checkedBeverages.length == 0) {
        console.log("No checkbox is checked...");
        alert("Nothing selected. Please make your selection!");
        return false;
    } else {
        for (let i = 0; i < $checkedBeverages.length; i++) {
            let $beverageId = $checkedBeverages[i].value;
            let $selectedBeverage = $('input[data-id="'+$beverageId+'"]');
            let min = parseInt($selectedBeverage.attr("min")), max = parseInt($selectedBeverage.attr("max")),
                val = parseInt($selectedBeverage.val());
            if (val < min || val > max) {
                alert("Please choose a valid amount of required beverages!")
                return false;
            }
            let $order = {
                beverageId : $beverageId,
                orderAmount : val
            }
            $customerOrder.push($order);
        }
    }
    console.log(JSON.stringify($customerOrder));
    postFormData("customer_order", $customerOrder, customerOrderCreateConfirmation);
});

function customerOrderCreateConfirmation($data){
    let $dataContent = adjustContentPage();
    let columns = ["#", "Beverage Manufacturer", "Beverage Name", "Beverage Price", "Incentive Name", "Order Quantity"];
    createTableDefinition("Thank you for your order, Here's the confirmation!", null,
        $dataContent, columns);
    populateTable($data);
}

function createFormField($form, $inputType, $inputLabel, $inputName){
    let $field;
    if ($inputType == "text"){
        $field = $(".form-page .input-text").clone();
    }
    else if ($inputType == "number"){
        $field = $(".form-page .input-number").clone();
    }
    else if ($inputType == "radio"){
        $field = $(".form-page .input-radio").clone();
    }
    $form.append($field);
    $field.find(".input-label").text($inputLabel);
    $field.find("input").attr("name", $inputName);
}

function createCancelSubmitButtons($form, $resource){
    $buttons = $(".form-page .cancel-submit-buttons").clone();
    $form.append($buttons);
    $buttons.find(".cancel-button").attr("data-resource", $resource);
    $buttons.find(".submit-button").attr("data-resource", $resource);
}

function postMethodCallback($data){
    console.log($data);
    let $tableBody = $(".dynamic-content tbody");
    let $rowNumber = $tableBody.find("tr").length + 1;
    let $newRow, $actionColumn = populateActionColumn($data);
    if ($data.resource == "incentives"){
        $newRow = '<tr><td>'+$rowNumber+'</td><td>'+$data.id+'</td><td>'+$data.name+'</td><td>'+
            $data.incentive_type+'</td>'+$actionColumn+'</tr>';
        $tableBody.append($newRow);
    }
    else if ($data.resource == "beverages"){
        $data.incentiveId = $data.incentiveDTO != null ? $data.incentiveDTO.id : "-";
        $data.incentiveName = $data.incentiveDTO != null ? $data.incentiveDTO.name : "-";
        $data.incentiveType = $data.incentiveDTO != null ? $data.incentiveDTO.incentive_type : "-";
        $newRow = '<tr><td>'+$rowNumber+'</td><td>'+$data.id+'</td><td>'+$data.name+'</td><td>'+
            $data.manufacturer+'</td><td>'+$data.quantity+'</td><td>'+$data.price+'</td><td>'+$data.incentiveId+
            '</td><td>'+$data.incentiveName+'</td><td>'+$data.incentiveType+'</td>'+$actionColumn+'</tr>';
        $tableBody.append($newRow);
    }
    $(".dynamic-content").show();
    $(".dynamic-form").remove();
}

function populateActionColumn($data){
    let $actionColumn = '<td><p><a class="btn btn-primary update" data-resource="'+$data.resource+'" ' +
        'data-id="'+$data.id+'">Update</a> <a class="btn btn-primary delete" ' +
        'data-resource="'+$data.resource+'" data-id="'+$data.id+'">Delete</a> </p></td>';
    return $actionColumn;
}

function postFormData($resource, $data, postMethodCallback){
    $.ajax({
        url: '/' + $resource,
        type: 'POST',
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify($data),
        success: function (data, textStatus, jqxhr) {
            console.log("request successful");
            data.status = textStatus;
            data.statusCode = jqxhr.status;
            data.resource = $resource;
            postMethodCallback(data);
        },
        error: function (jqxhr){
            console.log("request failed");
            let data = jqxhr.responseJSON;
            data.status = jqxhr.statusText;
            data.statusCode = jqxhr.status;
            data.resource = $resource;
            postMethodCallback(data);
        }
    });
}