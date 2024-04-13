interface GameState {
  name: string;
  footer: string;
  cells: Cell[];
  plugins: Plugin[];
  numColStyle: string;
  currentPlayer: string;
  gameOverMsg: string;
  showDropdown: boolean;
  currentPhase: string; // Added to track the current phase of the game.
  selectedWorker: Worker; // Added to track the selected worker.
}

interface Worker {
  x: number;
  y: number;
  playerID?: number;
}

interface Cell {
  text: string;
  playable: boolean;
  x: number;
  y: number;
}

interface Plugin {
    name: string;
}
  
export type { GameState, Cell, Plugin }