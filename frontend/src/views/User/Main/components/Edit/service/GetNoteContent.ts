import axios from "../../../../../../axios";

export const getNoteContent = async (NoteId: number) => {
    try {
        const response = await axios(
            `user/note/context`,
            {
                params: {
                    noteId: NoteId
                }
            }
        );
        return response.data.data;
    } catch (err) {
        console.error(err);
    }
}