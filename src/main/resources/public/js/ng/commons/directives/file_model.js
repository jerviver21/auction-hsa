angular.module('auctionhsa.commons')
	//$parse: Convierte una expresion de angular en una funcion (Es como una especie de reflection)
	.directive('fileModel', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        link: function($scope, $element, $attrs) {
        	//En este caso se convierte file-model="ctrl.file", osea ctrl.file se convierte en una funcion
            var model = $parse($attrs.fileModel);
            //assign se usa para poder asignarle valores a ctrl.file  (similar al invoke(method, param) de java)
            var modelSetter = model.assign;
            
            //$element.on, lo que hace es unir el elemento a una funcion, que puede ser un click, mouseenter, change (equivalente al onchange de html)
            $element.on('change', function(){
                $scope.$apply(function(){
                	//Le asigna el archivo a ctrl.file
                    modelSetter($scope, $element[0].files[0]);
                });
            });
            //Basicamente lo ultimo es un onChange, es decir cuando el elemento cambia, se carga el archivo, 
            //el scope se modificara  asignandole todo el file a ctrl.file
        }
    };
}]);