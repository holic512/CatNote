//
import {useRightSelectNodeId} from "../../Pinia/RightSelectNodeId.ts";
import {AddNote} from "./AddNote.ts";

export const onRightNFAddNote = () => {
    const selectId = useRightSelectNodeId().GetSelectNodeId();
    AddNote(selectId).then();
}