const API_URL = "http://localhost:8080/api/todos";

const taskForm = document.getElementById("taskForm");
const taskInput = document.getElementById("taskInput");
const taskList = document.getElementById("taskList");
const errorMsg = document.getElementById("errorMsg");

// ---- Cargar tareas ----
async function loadTasks() {
  taskList.innerHTML = "";
  const res = await fetch(API_URL);
  const todos = await res.json();

  todos.forEach(todo => renderTask(todo));
}

// ---- Renderizar una tarea ----
function renderTask(todo) {
  const li = document.createElement("li");
  li.className = "list-group-item d-flex justify-content-between align-items-center";
  li.innerHTML = `
    <span class="${todo.done ? 'text-decoration-line-through text-muted' : ''}">
      ${todo.title}
    </span>
    <div class="btn-group">
      <button class="btn btn-sm btn-outline-success" onclick="toggleTask(${todo.id})">✔</button>
      <button class="btn btn-sm btn-outline-danger" onclick="deleteTask(${todo.id})">🗑</button>
    </div>
  `;
  taskList.appendChild(li);
}

// ---- Crear tarea ----
taskForm.addEventListener("submit", async (e) => {
  e.preventDefault();
  errorMsg.textContent = "";

  const title = taskInput.value.trim();
  if (!title) return;

  try {
    const res = await fetch(API_URL, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify({ title }),
    });

    if (!res.ok) {
      const err = await res.json();
      throw new Error(err.error || "Unknown error");
    }

    const todo = await res.json();
    renderTask(todo);
    taskInput.value = "";
  } catch (err) {
    errorMsg.textContent = err.message;
  }
});

// ---- Alternar estado ----
async function toggleTask(id) {
  const res = await fetch(`${API_URL}/${id}/toggle`, { method: "PUT" });
  if (res.ok) {
    loadTasks();
  } else {
    const err = await res.json();
    alert(err.error || "Error toggling task");
  }
}

// ---- Eliminar tarea ----
async function deleteTask(id) {
  const res = await fetch(`${API_URL}/${id}`, { method: "DELETE" });
  if (res.status === 204) {
    loadTasks();
  } else {
    const err = await res.json();
    alert(err.error || "Error deleting task");
  }
}

// ---- Inicializar ----
loadTasks();
