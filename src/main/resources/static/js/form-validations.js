document.addEventListener('DOMContentLoaded', function() {
    // Validação de formulários Bootstrap
    const forms = document.querySelectorAll('.needs-validation');

    Array.prototype.slice.call(forms).forEach(function(form) {
        form.addEventListener('submit', function(event) {
            if (!form.checkValidity()) {
                event.preventDefault();
                event.stopPropagation();
            }

            form.classList.add('was-validated');
        }, false);
    });

    // Validação customizada para campos numéricos
    document.querySelectorAll('input[type="number"]').forEach(input => {
        input.addEventListener('input', function() {
            if (this.validity.rangeUnderflow) {
                this.setCustomValidity(`O valor mínimo é ${this.min}`);
            } else {
                this.setCustomValidity('');
            }
        });
    });
});