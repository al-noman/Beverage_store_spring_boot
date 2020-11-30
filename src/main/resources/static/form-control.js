$(document).on("click", ".create-button", function (){
    let $form = createFormContent();
    let $resource = $(this).attr("data-resource");
    createFormDefinition($resource, $form, "New Beverage", null, "create");
});

$(document).on("click", ".btn.update", function (){
    let $form = createFormContent();
    let $resource = $(this).data("resource");
    let $row = $(this).closest("tr");
    let $data, $title, $dataArray = [];
    $row.find("td").each(function (){
        $dataArray.push($(this).text());
    });

    if ($resource == "beverages"){
        $data = {
            id: $dataArray[1],
            beverage_name: $dataArray[2],
            beverage_manufacturer: $dataArray[3],
            beverage_quantity: $dataArray[4],
            beverage_price: $dataArray[5],
            incentiveId: $dataArray[6],
            incentiveName: $dataArray[7],
            incentiveType: $dataArray[8]
        }
        $title = "Update Beverage";
    }
    else if ($resource == "incentives"){
        $data = {
            id: $dataArray[1],
            incentive_name: $dataArray[2],
            incentive_type: $dataArray[3]
        }
        $title = "Update Incentive";
    }
    createFormDefinition($resource, $form, $title, $data, "update");
});

$(document).on("click", ".btn.delete", function (){
    if (confirm("Are you sure you want to delete this item?")){
        let $id = $(this).data("id");
        let $resource = $(this).data("resource") + "/" + $id;
        deleteResource($resource);
    }
});

$(document).on("click", ".cancel-button", function (){
    $(".dynamic-content").show();
    $(".dynamic-form").remove();
});

$(document).on("click", ".submit-button", function (e){
    e.preventDefault();
    let $resource = $(this).data("resource");
    let $action = $(this).data("action");
    let $callbackMethod, $httpMethod, $data;

    if ($resource == "beverages"){
        let $name = $('input[name="beverage_name"]').val();
        let $manufacturer = $('input[name="beverage_manufacturer"]').val();
        let $quantity = $('input[name="beverage_quantity"]').val();
        let $price = $('input[name="beverage_price"]').val();
        $data = {
            name: $name,
            manufacturer: $manufacturer,
            quantity: $quantity,
            price: $price
        }
        if ($action == 'update'){
            let $id = $(this).data("id");
            $data.id = $id;
            let $selectedIncentive = $(".dynamic-form .custom-select option:selected");
            if ($selectedIncentive.val() != -1){
                let incentiveDto = {
                    "id": $selectedIncentive.data("id"),
                    "incentive_type": $selectedIncentive.data("type"),
                    "version": $selectedIncentive.data("version"),
                    "name": $selectedIncentive.val()
                }
                $data.incentiveDTO = incentiveDto;
            }
            $resource = $resource + "/"+$id;
            $callbackMethod = updateMethodCallback;
            $httpMethod = 'PUT';
        }
        else {
            $callbackMethod = postMethodCallback;
            $httpMethod = 'POST';
        }
    }
    else if ($resource == "incentives"){
        let $name = $('input[name="incentive_name"]').val();
        let $incentive_type = $('input[name="incentive_type"]:checked').val();
        if (!$incentive_type){
            $incentive_type = $(this).data("type");
        }
        $data = {
            name: $name,
            incentive_type: $incentive_type
        }
        if ($action == 'update') {
            let $id = $(this).data("id");
            $data.id = $id;
            $resource = $resource + "/"+$id;
            $callbackMethod = updateMethodCallback;
            $httpMethod = 'PUT';
        }
        else {
            $callbackMethod = postMethodCallback;
            $httpMethod = 'POST';
        }
    }
    postFormData($resource, $data, $callbackMethod, $httpMethod);
});

$(document).on("click", ".order-button", function (){
    let $customerOrder = [];
    let $checkedBeverages = $("input[name='selectedBeverage']:checked");
    if ($checkedBeverages.length == 0) {
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
    postFormData("customer_order", $customerOrder, customerOrderCreateConfirmation, 'POST');
});

function createFormContent(){
    $(".dynamic-content").hide();
    let $form = $(".form-page").clone();
    $form.addClass("dynamic-form");
    $form.removeClass("form-page")
    $(".container").append($form);
    $form.find("form").empty();
    $form.show();
    return $form;
}

function createFormDefinition($resource, $form, $titleText, $data, $action){
    if ($resource == "beverages"){
        $form.find(".form-title").text($titleText);
        createFormField($form, "text", "Beverage Name", "beverage_name", $data);
        createFormField($form, "text", "Beverage Manufacturer", "beverage_manufacturer", $data);
        createFormField($form, "number", "Quantity", "beverage_quantity", $data);
        createFormField($form, "number", "Price", "beverage_price", $data);

        if ($action == 'update'){
            $.get( "/incentives", function( $result ) {
                populateIncentivesInDropDown($result, $data.incentiveId)
            }, "json" );
        }
        createCancelSubmitButtons($form, $resource, $action, $data);
    }
    else if ($resource == "incentives"){
        $form.find(".form-title").text($titleText);
        createFormField($form, "text", "Incentive Name", "incentive_name", $data);
        if ($action == "create"){
            createFormField($form, "radio", "Incentive type", "incentive_type", $data);
        }
        createCancelSubmitButtons($form, $resource, $action, $data);
    }
}

function populateIncentivesInDropDown($data, $incentiveId){
    let $selectDiv = $(".form-page .input-select").clone();
    let $selectBlock = $selectDiv.find(".custom-select");
    $data.forEach($incentive => {
        let selectStr = "";
        if ($incentive.id == $incentiveId){
            selectStr = "selected";
        }
        let $option = '<option value="'+$incentive.name+'" data-id="'+$incentive.id+'" data-type="'+
            $incentive.incentive_type+'" data-version="'+$incentive.version+'" '+selectStr+'>'+$incentive.name+'</option>';
        $selectBlock.append($option);
    });
    $(".dynamic-form .cancel-submit-buttons").before($selectDiv);
}

function customerOrderCreateConfirmation($data){
    let $dataContent = adjustContentPage();
    let columns = ["#", "Beverage Manufacturer", "Beverage Name", "Beverage Price", "Incentive Name", "Order Quantity"];
    createTableDefinition("Thank you for your order, Here's the confirmation!", null,
        $dataContent, columns);
    populateTable($data);
}

function createFormField($form, $inputType, $inputLabel, $inputName, $data){
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
    if ($data){
        $field.find("input").val($data[$inputName]);
    }
}

function createCancelSubmitButtons($form, $resource, $action, $data){
    $buttons = $(".form-page .cancel-submit-buttons").clone();
    $form.append($buttons);
    $buttons.find(".cancel-button").attr("data-resource", $resource);
    $buttons.find(".submit-button").attr("data-resource", $resource);
    $buttons.find(".submit-button").attr("data-action", $action);
    if ($data){
        $buttons.find(".submit-button").attr("data-id", $data.id);
    }
    if ($resource = "incentives" && $action == "update"){
        $buttons.find(".submit-button").attr("data-type", $data.incentive_type);
    }
}

function postMethodCallback($data){
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

function updateMethodCallback($data){
    let $idColumn = $(".dynamic-content td:nth-child(2)").filter(function(){
        return $(this).text() == $data.id;
    });
    let $targetRow = $idColumn.closest("tr");
    if ($data.resource.includes("beverages")){
        $targetRow.find("td:nth-child(3)").text($data.name);
        $targetRow.find("td:nth-child(4)").text($data.manufacturer);
        $targetRow.find("td:nth-child(5)").text($data.quantity);
        $targetRow.find("td:nth-child(6)").text($data.price);
        if ($data.incentiveDTO){
            $targetRow.find("td:nth-child(7)").text($data.incentiveDTO.id);
            $targetRow.find("td:nth-child(8)").text($data.incentiveDTO.name);
            $targetRow.find("td:nth-child(9)").text($data.incentiveDTO.incentive_type);
        }
    }
    else if ($data.resource.includes("incentives")){
        $targetRow.find("td:nth-child(3)").text($data.name);
        $targetRow.find("td:nth-child(4)").text($data.incentive_type);
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

function postFormData($resource, $data, postMethodCallback, $httpMethod){
    $.ajax({
        url: '/' + $resource,
        type: $httpMethod,
        contentType: "application/json",
        dataType: "json",
        data: JSON.stringify($data),
        success: function (data, textStatus, jqxhr) {
            data.status = textStatus;
            data.statusCode = jqxhr.status;
            data.resource = $resource;
            postMethodCallback(data);
            showStatusMessage($resource, $httpMethod, "success");
        },
        error: function (jqxhr){
            let data = jqxhr.responseJSON;
            data.status = jqxhr.statusText;
            data.statusCode = jqxhr.status;
            data.resource = $resource;
            postMethodCallback(data);
            showStatusMessage($resource, $httpMethod, "error");
        }
    });
}

function deleteResource($resource){
    $.ajax({
        type: "DELETE",
        url: $resource,
        success: function () {
            if ($resource.includes("beverage")){
                $(".menu-section").find("p.btn[data-menu='beverageMenu']").click();
            }
            else if ($resource.includes("incentives")){
                $(".menu-section").find("p.btn[data-menu='incentiveMenu']").click();
            }
            showStatusMessage($resource, "DELETE", "success");
        },
        error: function (err) {
            console.log(err);
            showStatusMessage($resource, "DELETE", "error");
        }
    });
}

function showStatusMessage($resource, $httpMethod, $status){
    let replaceWord = "", message = "";
    if ($resource.includes("beverages")){
        $resource = "Beverage";
    }
    else if ($resource.includes("incentives")){
        $resource = "Incentive";
    }
    if ($httpMethod == "POST"){
        replaceWord = " created ";
    }
    else if ($httpMethod == "PUT"){
        replaceWord = " updated ";
    }
    else if ($httpMethod == "DELETE"){
        replaceWord = " deleted ";
    }

    if ($status == "success"){
        message = $resource + replaceWord + "successfully";
        $('.text-success').text(message);
        $('.text-success').fadeIn();
        setTimeout(function() {
            $('.text-success').fadeOut("slow");
        }, 2000 );
    }
    else {
        message = $resource + " couldn't be" + replaceWord;
        $('.text-danger').text(message);
        $('.text-danger').fadeIn();
        setTimeout(function() {
            $('.text-danger').fadeOut("slow");
        }, 2000 );
    }
}