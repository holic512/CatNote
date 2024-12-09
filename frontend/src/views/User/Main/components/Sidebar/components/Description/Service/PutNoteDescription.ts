import axios from "@/axios";

export const putNoteDescription = async (noteId: number, noteDescription: string) => {
    try {
        const response = await axios.put(
            "user/noteTree/NoteSummary",
            {
                noteId: noteId,
                noteDescription: noteDescription,
            }
        )
        return response.data.status
    } catch (e) {
        console.error(e);
    }
}