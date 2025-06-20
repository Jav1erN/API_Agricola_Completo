const API = "http://localhost:8080";

let cultivoEnEdicion = null;
let parcelaEnEdicion = null;
let riegoEnEdicion = null;
let fertilizacionEnEdicion = null;

// ----------------- CULTIVOS ------------------
function mostrarCultivos() {
  fetch(`${API}/cultivos`)
    .then(res => res.json())
    .then(data => {
      const lista = document.getElementById("cultivo-list");
      const select = document.getElementById("cultivoSelect");
      lista.innerHTML = "";
      select.innerHTML = '<option value="">-- Selecciona un cultivo --</option>';

      data.forEach(c => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `
          <span><strong>${c.nombre}</strong> (${c.tipo}) - ${c.fechaSiembra || "Sin fecha"}</span>
          <div>
            <button class="btn btn-sm btn-warning me-1" onclick="editarCultivo(${c.id})">✏️</button>
            <button class="btn btn-sm btn-danger" onclick="eliminarCultivo(${c.id})">🗑️</button>
          </div>
        `;
        lista.appendChild(li);

        const option = document.createElement("option");
        option.value = c.id;
        option.textContent = c.nombre;
        select.appendChild(option);
      });
    });
}

function editarCultivo(id) {
  fetch(`${API}/cultivos/${id}`)
    .then(res => res.json())
    .then(c => {
      cultivoEnEdicion = id;
      document.getElementById("nombre").value = c.nombre;
      document.getElementById("tipo").value = c.tipo;
      document.getElementById("fechaSiembra").value = c.fechaSiembra;
    });
}

document.getElementById("cultivo-form").addEventListener("submit", e => {
  e.preventDefault();
  const cultivo = {
    nombre: document.getElementById("nombre").value,
    tipo: document.getElementById("tipo").value,
    fechaSiembra: document.getElementById("fechaSiembra").value
  };
  const metodo = cultivoEnEdicion ? "PUT" : "POST";
  const url = cultivoEnEdicion ? `${API}/cultivos/${cultivoEnEdicion}` : `${API}/cultivos`;
  fetch(url, {
    method: metodo,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(cultivo)
  }).then(() => {
    mostrarCultivos();
    document.getElementById("cultivo-form").reset();
    cultivoEnEdicion = null;
  });
});

function eliminarCultivo(id) {
  if (confirm("¿Eliminar este cultivo?")) {
    fetch(`${API}/cultivos/${id}`, { method: "DELETE" }).then(mostrarCultivos);
  }
}

// ----------------- PARCELAS ------------------
function mostrarParcelas() {
  fetch(`${API}/parcelas`)
    .then(res => res.json())
    .then(data => {
      const lista = document.getElementById("parcela-list");
      lista.innerHTML = "";
      data.forEach(p => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `
          <span><strong>${p.nombre}</strong> - ${p.ubicacion} (${p.area} ha) 
          <br><small class="text-muted">Cultivo: ${p.cultivo?.nombre || "Sin asignar"}</small></span>
          <div>
            <button class="btn btn-sm btn-warning me-1" onclick="editarParcela(${p.id})">✏️</button>
            <button class="btn btn-sm btn-danger" onclick="eliminarParcela(${p.id})">🗑️</button>
          </div>
        `;
        lista.appendChild(li);
      });
    });
}

function editarParcela(id) {
  fetch(`${API}/parcelas/${id}`)
    .then(res => res.json())
    .then(p => {
      parcelaEnEdicion = id;
      document.getElementById("nombreParcela").value = p.nombre;
      document.getElementById("ubicacion").value = p.ubicacion;
      document.getElementById("area").value = p.area;
      document.getElementById("cultivoSelect").value = p.cultivo?.id || "";
    });
}

document.getElementById("parcela-form").addEventListener("submit", e => {
  e.preventDefault();
  const cultivoId = document.getElementById("cultivoSelect").value;
  const parcela = {
    nombre: document.getElementById("nombreParcela").value,
    ubicacion: document.getElementById("ubicacion").value,
    area: parseFloat(document.getElementById("area").value),
    cultivo: cultivoId ? { id: parseInt(cultivoId) } : null
  };
  const metodo = parcelaEnEdicion ? "PUT" : "POST";
  const url = parcelaEnEdicion ? `${API}/parcelas/${parcelaEnEdicion}` : `${API}/parcelas`;
  fetch(url, {
    method: metodo,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(parcela)
  }).then(() => {
    mostrarParcelas();
    document.getElementById("parcela-form").reset();
    parcelaEnEdicion = null;
  });
});

function eliminarParcela(id) {
  if (confirm("¿Eliminar esta parcela?")) {
    fetch(`${API}/parcelas/${id}`, { method: "DELETE" }).then(mostrarParcelas);
  }
}

// ----------------- RIEGOS ------------------
function mostrarRiegos() {
  fetch(`${API}/riegos`)
    .then(res => res.json())
    .then(data => {
      const lista = document.getElementById("riego-list");
      lista.innerHTML = "";
      data.forEach(r => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `
          <span>🌧️ ${r.fecha} - ${r.cantidadAgua} L (${r.parcela?.nombre})</span>
          <div>
            <button class="btn btn-sm btn-warning me-1" onclick="editarRiego(${r.id})">✏️</button>
            <button class="btn btn-sm btn-danger" onclick="eliminarRiego(${r.id})">🗑️</button>
          </div>
        `;
        lista.appendChild(li);
      });
    });
}

function editarRiego(id) {
  fetch(`${API}/riegos/${id}`)
    .then(res => res.json())
    .then(r => {
      riegoEnEdicion = id;
      document.getElementById("fechaRiego").value = r.fecha;
      document.getElementById("cantidadAgua").value = r.cantidadAgua;
      document.getElementById("parcelaSelectRiego").value = r.parcela?.id || "";
    });
}

document.getElementById("riego-form").addEventListener("submit", e => {
  e.preventDefault();
  const riego = {
    fecha: document.getElementById("fechaRiego").value,
    cantidadAgua: parseFloat(document.getElementById("cantidadAgua").value),
    parcela: { id: parseInt(document.getElementById("parcelaSelectRiego").value) }
  };
  const metodo = riegoEnEdicion ? "PUT" : "POST";
  const url = riegoEnEdicion ? `${API}/riegos/${riegoEnEdicion}` : `${API}/riegos`;
  fetch(url, {
    method: metodo,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(riego)
  }).then(() => {
    mostrarRiegos();
    document.getElementById("riego-form").reset();
    riegoEnEdicion = null;
  });
});

function eliminarRiego(id) {
  if (confirm("¿Eliminar este riego?")) {
    fetch(`${API}/riegos/${id}`, { method: "DELETE" }).then(mostrarRiegos);
  }
}

// ----------------- FERTILIZACIONES ------------------
function mostrarFertilizaciones() {
  fetch(`${API}/fertilizaciones`)
    .then(res => res.json())
    .then(data => {
      const lista = document.getElementById("fertilizacion-list");
      lista.innerHTML = "";
      data.forEach(f => {
        const li = document.createElement("li");
        li.className = "list-group-item d-flex justify-content-between align-items-center";
        li.innerHTML = `
          <span>🧪 ${f.fecha} - ${f.tipoFertilizante} (${f.dosis} kg) en ${f.parcela?.nombre}</span>
          <div>
            <button class="btn btn-sm btn-warning me-1" onclick="editarFertilizacion(${f.id})">✏️</button>
            <button class="btn btn-sm btn-danger" onclick="eliminarFertilizacion(${f.id})">🗑️</button>
          </div>
        `;
        lista.appendChild(li);
      });
    });
}

function editarFertilizacion(id) {
  fetch(`${API}/fertilizaciones/${id}`)
    .then(res => res.json())
    .then(f => {
      fertilizacionEnEdicion = id;
      document.getElementById("fechaFertilizacion").value = f.fecha;
      document.getElementById("tipoFertilizante").value = f.tipoFertilizante;
      document.getElementById("dosis").value = f.dosis;
      document.getElementById("parcelaSelectFertilizacion").value = f.parcela?.id || "";
    });
}

document.getElementById("fertilizacion-form").addEventListener("submit", e => {
  e.preventDefault();
  const fertilizacion = {
    fecha: document.getElementById("fechaFertilizacion").value,
    tipoFertilizante: document.getElementById("tipoFertilizante").value,
    dosis: parseFloat(document.getElementById("dosis").value),
    parcela: { id: parseInt(document.getElementById("parcelaSelectFertilizacion").value) }
  };
  const metodo = fertilizacionEnEdicion ? "PUT" : "POST";
  const url = fertilizacionEnEdicion ? `${API}/fertilizaciones/${fertilizacionEnEdicion}` : `${API}/fertilizaciones`;
  fetch(url, {
    method: metodo,
    headers: { "Content-Type": "application/json" },
    body: JSON.stringify(fertilizacion)
  }).then(() => {
    mostrarFertilizaciones();
    document.getElementById("fertilizacion-form").reset();
    fertilizacionEnEdicion = null;
  });
});

function eliminarFertilizacion(id) {
  if (confirm("¿Eliminar esta fertilización?")) {
    fetch(`${API}/fertilizaciones/${id}`, { method: "DELETE" }).then(mostrarFertilizaciones);
  }
}

// ----------------- INICIALIZACIÓN ------------------
function cargarParcelasParaFormularios() {
  fetch(`${API}/parcelas`)
    .then(res => res.json())
    .then(data => {
      const selects = [
        document.getElementById("parcelaSelectRiego"),
        document.getElementById("parcelaSelectFertilizacion")
      ];
      selects.forEach(select => {
        select.innerHTML = '<option value="">-- Selecciona una parcela --</option>';
        data.forEach(p => {
          const option = document.createElement("option");
          option.value = p.id;
          option.textContent = p.nombre;
          select.appendChild(option);
        });
      });
    });
}

mostrarCultivos();
mostrarParcelas();
mostrarRiegos();
mostrarFertilizaciones();
cargarParcelasParaFormularios();
